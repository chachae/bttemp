package com.chachae.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

/**
 * @author chachae
 * @date 2019/8/16
 */
@Slf4j
public class shiroPassword {

  @Test
  public void createPassword() {
    // 加密方式
    String algorithmName = "MD5";
    // 加密的字符串
    String source = "123";
    // 加密的次数,可以进行多次的加密操作
    int hashIterations = 2;
    // 通过SimpleHash 来进行加密操作
    SimpleHash hash = new SimpleHash(algorithmName, source, null, hashIterations);
    System.out.println(hash.toString());
  }
}
