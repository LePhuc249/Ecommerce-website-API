package nashtech.phucldh.ecommerce.converter;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nashtech.phucldh.ecommerce.dto.AccountDTO;
import nashtech.phucldh.ecommerce.entity.Account;

@Component
public class AccountConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AccountDTO convertAccountToDto(Account account) {
		AccountDTO dto = modelMapper.map(account, AccountDTO.class);
		Set<String> roles = account.getRoles().stream()
		        .map(role -> role.getName().name())
		        .collect(Collectors.toSet());
		dto.setRoles(roles);
	    return dto;
	}
}
