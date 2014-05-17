package com.mike.londonmet.entity;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: mikeholmes
 */
public class ProductTest {
	private static Logger logger = Logger.getLogger("ProductTest");

	private Set<ConstraintViolation<Product>> violations;
	private ValidatorFactory validatorFactory;
	private Validator validator;
	private Product product;

	@BeforeClass
	public static void oneTimeSetup() {
		logger.fine("Running one-time only setup for Product Class test");
	}

	@Before
	public void setup() {
		logger.fine("Running setup before each test for Product Class test");
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		violations = null;
		product = new Product();
	}

	@Test
	public void newObjectIsNotNull() {
		assertNotNull(new Product());
	}

	@Test
	public void newObject_HasNullTitleByDefault() {
		assertNull(product.getTitle());
	}

	@Test
	public void newObject_HasNullDescriptionByDefault() {
		assertNull(product.getLongDescription());
	}

	@Test
	public void newObject_FailsValidation_ReturnsTwoViolations() {
		violations = validator.validate(product);
		assertEquals("Object properties cannot be set to Null - should return 2 violations (title & description)", violations.size(), 2);
	}

	@Test
	public void settingNullTitle_FailsValidation_ReturnsOneViolation() {
		product.setTitle(null);
		violations = validator.validateProperty(product, "title");
		assertEquals("Title cannot be null - should return 1 violation (null)", violations.size(), 1);
	}

	@Test
	public void settingNullDescription_FailsValidation_ReturnsOneViolation() {
		product.setLongDescription(null);
		violations = validator.validateProperty(product, "description");
		assertEquals("Description cannot be null - should return 1 violation (null)", violations.size(), 1);
	}

	@Test
	public void settingTitleLengthOfOne_FailsValidation_ReturnsOneViolation() {
		product.setTitle(RandomStringUtils.random(1, true, false));     // Length of one with letters, no numbers
		violations = validator.validateProperty(product, "title");
		assertEquals("Title has minimum length of two - should return 1 violation (length)", violations.size(), 1);
	}

	@Test
	public void settingTitleLengthOfFiftyOne_FailsValidation_ReturnsOneViolation() {
		product.setTitle(RandomStringUtils.random(51, true, false));
		violations = validator.validateProperty(product, "title");
		assertEquals("Title has minimum length of two - should return 1 violation (length)", violations.size(), 1);
	}

	@Test
	public void settingDescriptionLengthOfFour_FailsValidation_ReturnsOneViolation() {
		product.setLongDescription(RandomStringUtils.random(4, true, false));     // Length of one with letters, no numbers
		violations = validator.validateProperty(product, "description");
		assertEquals("Title has minimum length of two - should return 1 violation (length)", violations.size(), 1);
	}

	@Test
	public void settingDescriptionLengthOfMoreThan4096_FailsValidation_ReturnsOneViolation() {
		product.setLongDescription(RandomStringUtils.random(4097, true, false));
		violations = validator.validateProperty(product, "description");
		assertEquals("Title has minimum length of two - should return 1 violation (length)", violations.size(), 1);
	}

	@Test
	public void constructorSetTitleOfNull_FailsValidation_ReturnsOneViolation() {
		product = new Product(null, null);
		violations = validator.validateProperty(product, "title");
		assertEquals("Null Title set through constructor should fail validation - null violation", violations.size(), 1);
	}

	@Test
	public void constructorSetDescriptionOfNull_FailsValidation_ReturnsOneViolation() {
		product = new Product(null, null);
		violations = validator.validateProperty(product, "description");
		assertEquals("Null Description set through constructor should fail validation - null violation", violations.size(), 1);
	}

	@Test
	public void constructorSetTitleTooShort_FailsValidation_ReturnsOneViolation() {
		product = new Product(RandomStringUtils.random(1, true, false), null);
		violations = validator.validateProperty(product, "title");
		assertEquals("Too short title set through constructor should fail validation - length violation", violations.size(), 1);
	}

	@Test
	public void constructorSetTitleTooLong_FailsValidation_ReturnsOneViolation() {
		product = new Product(RandomStringUtils.random(51, true, false), null);
		violations = validator.validateProperty(product, "title");
		assertEquals("Too long title set through constructor should fail validation - length violation", violations.size(), 1);
	}

	@Test
	public void constructorSetDescriptionTooShort_FailsValidation_ReturnsOneViolation() {
		product = new Product(null, RandomStringUtils.random(4, true, false));
		violations = validator.validateProperty(product, "description");
		assertEquals("Too short description set through constructor should fail validation - length violation", violations.size(), 1);
	}

	@Test
	public void constructorSetDescriptionTooLong_FailsValidation_ReturnsOneViolation() {
		product = new Product(null, RandomStringUtils.random(4097, true, false));
		violations = validator.validateProperty(product, "title");
		assertEquals("Too long title set through constructor should fail validation - length violation", violations.size(), 1);
	}
}
