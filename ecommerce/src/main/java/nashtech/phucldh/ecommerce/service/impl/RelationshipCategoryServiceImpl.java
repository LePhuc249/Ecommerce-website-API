package nashtech.phucldh.ecommerce.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Relationship_Category;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.RelationshipCategoryRepository;
import nashtech.phucldh.ecommerce.service.RelationshipCategoryService;

@Service
public class RelationshipCategoryServiceImpl implements RelationshipCategoryService {

	@Autowired
	RelationshipCategoryRepository relationshipCategoryRepository;

	@Override
	public Relationship_Category getRelationshipCategoryByCategory(String category1, String category2) throws DataNotFoundException {
		Optional<Relationship_Category> result = relationshipCategoryRepository
				.findByCategoryid1OrCategoryid2(category1, category2);
		Relationship_Category theRelationship_Category = null;
		if (result.isPresent()) {
			theRelationship_Category = result.get();
		} else {
			throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
		}
		return theRelationship_Category;
	}

}
