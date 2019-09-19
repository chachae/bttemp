package com.chachae.api.util;

import com.chachae.api.common.exception.ParamException;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * VO数据校验工具类<br>
 * 配合validator工具
 *
 * @author chachae
 * @date 2019/8/3
 */
public class BeanValidator {

  private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

  /**
   * @param t 任意类型数据
   * @param groups 类
   * @param <T> 任意返回结果
   * @return 错误信息
   */
  @SuppressWarnings("unchecked")
  private static <T> Map<String, String> validate(T t, Class... groups) {
    Validator validator = validatorFactory.getValidator();
    Set validateResult = validator.validate(t, groups);
    if (validateResult.isEmpty()) {
      return Collections.emptyMap();
    } else {
      LinkedHashMap errors = Maps.newLinkedHashMap();
      for (Object o : validateResult) {
        ConstraintViolation violation = (ConstraintViolation) o;
        errors.put(violation.getPropertyPath().toString(), violation.getMessage());
      }
      return errors;
    }
  }

  /**
   * List数据校验
   *
   * @param collection 任意数据
   * @return 错误信息
   */
  @SuppressWarnings("unchecked")
  private static Map<String, String> validateList(Collection<?> collection) {
    Preconditions.checkNotNull(collection);
    Iterator iterator = collection.iterator();
    Map errors;
    do {
      if (!iterator.hasNext()) {
        return Collections.emptyMap();
      }
      Object object = iterator.next();
      errors = validate(object);
    } while (errors.isEmpty());
    return errors;
  }

  /**
   * 任意数据校验
   *
   * @param first 第一个校验的数据
   * @param objects 第二个校验的数据
   * @return 返回给validateList处理
   */
  private static Map<String, String> validateObject(Object first, Object... objects) {
    if (objects != null && objects.length > 0) {
      return validateList(Lists.asList(first, objects));
    } else {
      return validate(first);
    }
  }

  /**
   * VO对象校验
   *
   * @param param 任意对象
   * @throws ParamException 抛出自定义数据异常
   */
  public static void check(Object param) throws ParamException {
    Map<String, String> map = BeanValidator.validateObject(param);
    if (!CollectionUtils.isEmpty(map)) {
      Iterator<String> iter = map.keySet().iterator();
      String value;
      List<Object> list = Lists.newArrayList();
      while (iter.hasNext()) {
        String key = iter.next();
        value = map.get(key);
        list.add(value);
      }
      throw new ParamException(list.toString().replace("[", "").replace("]", ""));
    }
  }
}
