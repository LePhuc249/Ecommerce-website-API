package nashtech.phucldh.ecommerce.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.entity.Relationship_Category;
import nashtech.phucldh.ecommerce.exception.RelationshipCategoryNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.RelationshipCategoryRepository;
import nashtech.phucldh.ecommerce.service.RelationshipCategoryService;

@Service
public class RelationshipCategoryServiceImpl implements RelationshipCategoryService{
	
	@Autowired
	RelationshipCategoryRepository relationshipCategoryRepository;

	@Override
	public Relationship_Category getRelationshipCategoryByCategory(String category1, String category2) {
		Optional<Relationship_Category> result = relationshipCategoryRepository.findByCategoryid1OrCategoryid2(category1, category2);
		Relationship_Category theRelationship_Category = null;
		if (result.isPresent()) {
			theRelationship_Category = result.get();
		} else {
			throw new RelationshipCategoryNotFoundException("Did not find relationship by category - " + category1 + " or " + category2);
		}
		return theRelationship_Category;
	}

}
