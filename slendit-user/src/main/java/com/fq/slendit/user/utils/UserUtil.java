package com.fq.slendit.user.utils;

import java.text.DecimalFormat;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;

import com.fq.slendit.user.entity.Address;
import com.fq.slendit.user.entity.User;
import com.fq.slendit.user.request.RegistrationRequest;
import com.fq.slendit.user.request.UpdateUserRequest;

public class UserUtil {

	public static User setUser(RegistrationRequest request) {

		User user = new User();

		user.setEmail(request.getEmail());
		user.setMobile(request.getMobile());
		user.setFirstName(request.getFirstName());
		user.setMiddleName(request.getMiddleName());
		user.setLastName(request.getLastName());
		user.setAboutMe(request.getAboutMe());
		user.setRole(request.getRole());

		user.setUserName(request.getUserName());
		user.setPassword(request.getPassword());

		user.setPhNoVerify("N");
		user.setIsEmailVerified("N");

		String otp = new DecimalFormat("000000").format(new Random().nextInt(999999));
		user.setOtp(otp);

		String uuid = UUID.randomUUID().toString();
		user.setVerificationCode(uuid);

		user.setCreated(DateUtil.getCurrentDateTime());
		user.setUpdated(DateUtil.getCurrentDateTime());

		user.setIsAddressAdded("N");

		return user;
	}

	public static User setUpdateUser(User user, @Valid UpdateUserRequest request) {

		user.setMobile(request.getMobile());
		user.setFirstName(request.getFirstName());
		user.setMiddleName(request.getMiddleName());
		user.setLastName(request.getLastName());
		user.setAboutMe(request.getAboutMe());
		user.setUserName(request.getUserName());
		user.setMobile(request.getMobile());

		Set<Address> addresses = user.getAddresses() == null ? new LinkedHashSet<Address>() : user.getAddresses();

		if ("YES".equalsIgnoreCase(request.getHomeAddress())) {
			Address homeAddress = addresses.stream().filter(addr -> "Heme address".equalsIgnoreCase(addr.getType()))
					.findFirst().orElse(new Address());

			if ("N".equals(user.getIsAddressAdded())) {
				user.setIsAddressAdded("Y");
			}
			if (homeAddress.getId() <= 0)
				homeAddress.setCreated(DateUtil.getCurrentDateTime());

			homeAddress.setUserId(request.getUserId());
			homeAddress.setStreetAddress(request.getHomeStreetAddress());
			homeAddress.setCity(request.getHomeCity());
			homeAddress.setState(request.getHomeState());
			homeAddress.setZip(request.getHomeZip());
			homeAddress.setCountry(request.getHomeCountry());
			homeAddress.setType("Home address");

			homeAddress.setUpdated(DateUtil.getCurrentDateTime());
			addresses.add(homeAddress);
		}

		if ("YES".equalsIgnoreCase(request.getWorkAddress())) {

			Address workAddress = addresses.stream().filter(addr -> "Work address".equalsIgnoreCase(addr.getType()))
					.findFirst().orElse(new Address());

			if (workAddress.getId() <= 0)
				workAddress.setCreated(DateUtil.getCurrentDateTime());

			workAddress.setUserId(request.getUserId());
			workAddress.setStreetAddress(request.getWorkStreetAddress());
			workAddress.setCity(request.getWorkCity());
			workAddress.setState(request.getWorkState());
			workAddress.setZip(request.getWorkZip());
			workAddress.setCountry(request.getWorkCountry());
			workAddress.setType("Work address");

			workAddress.setUpdated(DateUtil.getCurrentDateTime());
			addresses.add(workAddress);
		}

		user.setAddresses(addresses);

		return user;
	}
}
