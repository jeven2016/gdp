/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.modules.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class BaseCtrl {

  @RequestMapping("list/test2")
  public String list(){
    return "yes";
  }

  @RequestMapping
  public String list2(){
    return "list2";
  }

}
