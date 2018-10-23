/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.modules.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books/{kk}")
public class BookCtrl extends BaseCtrl {

  @RequestMapping("hello")
  public String hello() {
    return "hello";
  }
}
