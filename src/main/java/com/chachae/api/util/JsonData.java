package com.chachae.api.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用Json返回格式<br>
 * 可用于增加、删除、更新、查询等判断<br>
 * ret：布尔值，true：成功，false：失败
 *
 * @author chachae
 * @date 2019/7/13
 */
@Data
public class JsonData {

  /** ret：布尔值，用来指定操作成功或者失败，true：成功，false：失败 */
  private boolean ret;

  /** msg：存放自定义的返回消息 */
  private String msg;

  /** data：存放返回的数据，如：返回一个成员的全部信息 */
  private Object data;

  public JsonData(boolean ret) {
    this.ret = ret;
  }

  /**
   * 成功，默认true
   *
   * @param object 存放数据
   * @param msg 消息
   * @return json
   */
  public static JsonData success(Object object, String msg) {
    JsonData jsonData = new JsonData(true);
    jsonData.data = object;
    jsonData.msg = msg;
    return jsonData;
  }

  /**
   * 成功，默认true
   *
   * @param object 存放数据
   * @return json
   */
  public static JsonData success(Object object) {
    JsonData jsonData = new JsonData(true);
    jsonData.data = object;
    return jsonData;
  }

  /**
   * 成功，默认true
   *
   * @return json
   */
  public static JsonData success() {
    return new JsonData(true);
  }

  /**
   * 失败，默认false
   *
   * @param msg 消息
   * @return json
   */
  public static JsonData fail(String msg) {
    JsonData jsonData = new JsonData(false);
    jsonData.msg = msg;
    return jsonData;
  }

  /**
   * 其他类型的自定义json返回数据格式
   *
   * @return json
   */
  public Map<String, Object> toMap() {
    HashMap<String, Object> result = new HashMap<String, Object>();
    result.put("ret", ret);
    result.put("msg", msg);
    result.put("data", data);
    return result;
  }
}
