package com.openclassrooms.Project6.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class MainController {
	
	
	 @Autowired
	 	private UserRepository userRepository;
	 
	 @Autowired
		private FriendshipRepository friendshipRepository;
	 
	 @Autowired
	 	private TransactionsRepository transactionsRepository; 
	 
	 @Autowired
	 	private BankDAO bankDAO; 
	 
	 
	
	@GetMapping("/addUser")
	public String addUser(@ModelAttribute("userForm") UserForm userForm) {
		return "addUser";
	}
	
	@PostMapping("/addUser")
	public String addUser(Model model, @ModelAttribute("userForm") UserForm userForm) {
		List<User> users = userRepository.findByMail(userForm.getMail());
		if(users.size() != 1) {
			User newUser = new User();
			users.add(newUser);
			return "home";
		} else {
		User user = users.get(0);
			if(userForm.getMail().equals(user.getMail())){ 
				model.addAttribute("message", "Utilisateur deja existant");
				return "addUser";
		} else {
			return "home";
			}
		}
				
	} 
	
	
	
	@GetMapping("/loginUser")
	public String loginUser( Model model,@ModelAttribute("userForm") UserLogin userLogin, HttpServletRequest request) {
		try {
			getUserIdFromRequest(model, request);
		} catch (UserNotConnectedException e) {
			// will do nothing 
		}
		
		
		return "loginUser";
	}
	
	@PostMapping("/loginUser")
	public String loginUser(Model model, @ModelAttribute("userForm") UserLogin userLogin, HttpServletResponse response) {
		List<User> users = userRepository.findByMail(userLogin.getMail());
		if(users.size() != 1) {
			 model.addAttribute("message", "il n'existe pas un utilisateur unique correspondant a cet email.");
				return "loginUser";	
		} else {
			User user = users.get(0);
			if(userLogin.getPassword().equals(user.getPassword())) {
			    Cookie cookie = new Cookie("userId", ""+user.getId());
			    response.addCookie(cookie);
			    model.addAttribute("admin", user.getAdmin());
			    model.addAttribute("username", user.getName());
				model.addAttribute("useramount", user.getAmount());
				model.addAttribute("activeMenu", "home");
				return "home";
			} else if(userLogin.getPassword().equals(user.getPassword()) && userLogin.getMail() != user.getMail()) {
				model.addAttribute("message", "Addresse mail incorrecte.");
				return "loginUser";
				
			}
			
			else {
		        model.addAttribute("message", "Mot de passe incorrect.");
			return "loginUser";
			}
		}
	}

	
	
	@GetMapping("/addConnection")
	public String addConnection(Model model, HttpServletRequest request) {
		Long userId;
		try {
			userId = getUserIdFromRequest(model, request);
		} catch (UserNotConnectedException e) {
			return "loginUser";
		}
		User user = userRepository.findById(userId).get();
		List<Friendship> friendships = friendshipRepository.findByFirstfriendId(user.getId().intValue());
		Iterable<User> findAll = userRepository.findAll();
		List<User> nonFriends = new ArrayList();
		for (User current : findAll ) {
			if(!isFriend(current, friendships)) {
				if (current.getId() != user.getId()) {
					nonFriends.add(current);
				}
			} 
		}
		model.addAttribute("nonFriends", nonFriends);
		model.addAttribute("newFriend", new User());
		return "addConnection";
	}
		
	@GetMapping("/addMoney")
	public String addMoneyToUser (Model model) {
		model.addAttribute("newMoney", new NewMoney());
		
		return "addMoney";
	}
	
	@PostMapping("/addMoney")
	public RedirectView addMoneyToUser (@ModelAttribute("newMoney") NewMoney newMoney, HttpServletRequest request, Model model) {
		Long userId;
		try {
			userId = getUserIdFromRequest(model, request);
		} catch (UserNotConnectedException e) {
			return new RedirectView("loginUser");
		}
		User user = userRepository.findById(userId).get();		
		user.setAmount(user.getAmount() + newMoney.getAmount());
		userRepository.save(user);
		return new RedirectView("transfer");
	}
	
	
	
	@PostMapping("/profile")
	public RedirectView profile(Model model, @ModelAttribute("user") User user, HttpServletRequest request) {
		Long userId;
		try {
			userId = getUserIdFromRequest(model, request);
		} catch (UserNotConnectedException e) {
			return new RedirectView("loginUser");
		}
		User userTmp = userRepository.findById(userId).get();
		userTmp.setName(user.getName());
		userTmp.setAdress(user.getAdress());
		userTmp.setZip(user.getZip());
		userTmp.setCity(user.getCity());
		userRepository.save(userTmp);
	
		return new RedirectView("profile");
	}
	
	
	private boolean isFriend(User current, List<Friendship> friendships) {
		if(friendships != null) {
			for (Friendship friendship : friendships) {
				if(friendship.getSecondfriend_id() == current.getId()) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	@GetMapping("/bankBenefit")	
	public String bankBenefit ( HttpServletRequest request,  Model model) {
		Long userId;
		try {
			userId = getUserIdFromRequest(model, request);
		} catch (UserNotConnectedException e) {
			return "loginUser";
		}
		User user = userRepository.findById(userId).get();
		if(user.getAdmin() == false) {
			return "home";
		}
		int amount = bankDAO.getAmount();
		model.addAttribute("amount", amount);
		return "bankBenefit";
	}
	

	

	@GetMapping("/transfer")
	public String transfer(@ModelAttribute("transactions") Transactions transactions, HttpServletRequest request, Model model) {
		Long userId;
		try {
			userId = getUserIdFromRequest(model, request);
		} catch (UserNotConnectedException e) {
			return "loginUser";
		}
		User user = userRepository.findById(userId).get();
		
		model.addAttribute("username", user.getName());
		model.addAttribute("useramount", user.getAmount());
		List<Friendship> friendships = friendshipRepository.findByFirstfriendId(user.getId().intValue());
		List<User> friends = new ArrayList<>();
		for (Friendship friendship : friendships) {
			User friend = userRepository.findById(new Long(friendship.getSecondfriend_id())).get();
			friends.add(friend);
		}
		model.addAttribute("friends", friends);
		
		List<Transactions> transfer = transactionsRepository.findBySenderId(new Long (user.getId()));
		List<TransactionSummary> transactionSummaries = new ArrayList<>();
		for(Transactions transaction : transfer) {
			TransactionSummary tmp = new TransactionSummary();
			tmp.setDescription(transaction.getDescription());
			tmp.setAmount(transaction.getAmount());
			User friend = userRepository.findById(new Long(transaction.getReceiver_id())).get();
			Transactions friendTransaction = transactionsRepository.findById(new Long(transaction.getId())).get();
			tmp.setRecipientName(friend.getName());
			tmp.setDescription(friendTransaction.getDescription());
			tmp.setAmount(friendTransaction.getAmount());
			transactionSummaries.add(tmp);
			
			
		}
		model.addAttribute("transactions", transactionSummaries);
		model.addAttribute("transaction", new Transactions());
		model.addAttribute("activeMenu", "transfer");
		return "transfer";
		
		
	}
	private Long getUserIdFromRequest(Model model, HttpServletRequest request) throws UserNotConnectedException {
		model.addAttribute("admin", false);
		Cookie[] cookies = request.getCookies();
		String userId = null;		
		for (int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equalsIgnoreCase("userId")) {
				userId = cookies[i].getValue();
				if (userId == null || userId.length() == 0) {
					model.addAttribute("userForm", new UserForm());
					throw new UserNotConnectedException();
				}
				break;
			}
		} 
		User user = userRepository.findById(Long.parseLong(userId)).get();
		model.addAttribute("admin", user.getAdmin());
		return Long.parseLong(userId);
		
	}

	@GetMapping("/newTransfer")
	public String newTransfer(@ModelAttribute("transaction") Transactions transaction) {
		return "transfer";
	}
	
	@PostMapping("/submitConnection")
	public RedirectView submitConnection(Model model, @ModelAttribute("newFriend") User user, HttpServletRequest request) {
		Long userId;
		try {
			userId = getUserIdFromRequest(model, request);
		} catch (UserNotConnectedException e) {
			return new RedirectView("loginUser");
		}
		Friendship friendship = new Friendship();
		friendship.setFirstfriend_id(userId.intValue());
		friendship.setSecondfriend_id(user.getId().intValue());
		friendship.setId(friendshipRepository.count()+1);
		friendshipRepository.save(friendship);
		
		return new RedirectView("transfer");
	}
	
	@PostMapping("/newTransfer")
	@Transactional
	public String newTransfer(Model model, @ModelAttribute("transaction") Transactions transaction, HttpServletResponse response, HttpServletRequest request) throws SQLException {
		Long userId;
		try {
			userId = getUserIdFromRequest(model, request);
		} catch (UserNotConnectedException e) {
			return "loginUser";
		}
		User user = userRepository.findById(userId).get();
		transaction.setSender_id(userId);
		transaction.setId(getNewTransactionId());
		transaction.setDescription(transaction.getDescription());
		transactionsRepository.save(transaction);
		int bankPart = (int) (transaction.getAmount() * 0.05);
		bankDAO.addAmount(bankPart);
		user.setAmount(user.getAmount() - transaction.getAmount() - bankPart);
		userRepository.save(user);
		User receiver = userRepository.findById(transaction.getReceiver_id()).get();
		receiver.setAmount(receiver.getAmount() + transaction.getAmount());
		userRepository.save(receiver);
		String transactionsId = null;
		List<Transactions> transactions = transactionsRepository.findBySenderId(new Long (user.getId()));
		List<TransactionSummary> transactionSummaries = new ArrayList<>();
		for(Transactions trans : transactions) {
			TransactionSummary tmp = new TransactionSummary();
			tmp.setDescription(trans.getDescription());
			tmp.setAmount(trans.getAmount());
			User friend = userRepository.findById(new Long(trans.getReceiver_id())).get();
			Transactions friendTransaction = transactionsRepository.findById(new Long(trans.getId())).get();
			tmp.setRecipientName(friend.getName());
			tmp.setDescription(friendTransaction.getDescription());
			tmp.setAmount(friendTransaction.getAmount());
			transactionSummaries.add(tmp);
			
		}
		model.addAttribute("username", user.getName());
		model.addAttribute("useramount", user.getAmount());
		List<Friendship> friendships = friendshipRepository.findByFirstfriendId(user.getId().intValue());
		List<User> friends = new ArrayList<>();
		for (Friendship friendship : friendships) {
			User friend = userRepository.findById(new Long(friendship.getSecondfriend_id())).get();
			friends.add(friend);
		}
		model.addAttribute("friends", friends);
		model.addAttribute("transactions", transactionSummaries);
		model.addAttribute("transaction", new Transactions());
		
		
		return "transfer";
	}
	
	private Long getNewTransactionId() {
		return transactionsRepository.count() +1;
		
		
	}
	
	@GetMapping("/home")
	public String goHome(Model model, HttpServletRequest request) {
		Long userId;
		try {
			userId = getUserIdFromRequest(model, request);
		} catch (UserNotConnectedException e) {
			return "loginUser";
		}
		User user = userRepository.findById(userId).get();
		model.addAttribute("username", user.getName());
		model.addAttribute("useramount", user.getAmount());
		model.addAttribute("activeMenu", "home");
		return "home";
	}

	@GetMapping("/profile")
	public String profile(Model model, HttpServletRequest request) {
		Long userId;
		try {
			userId = getUserIdFromRequest(model, request);
		} catch (UserNotConnectedException e) {
			return "loginUser";
		}
		User user = userRepository.findById(userId).get();
		model.addAttribute("user", user);
		return "profile";
	}

	@PostMapping("/transfer")
	public String transfer(Model model, @ModelAttribute("transactions") Transactions transactions) {
		return "transfer";
	}
	
	
	@GetMapping("/logoff")
	public String logoff(Model model, HttpServletResponse response) {
		Cookie cookie = new Cookie("userId", "");
		model.addAttribute("userForm", new UserForm());
	    response.addCookie(cookie);
	    		return "loginUser";
	}
	
			


	
}
