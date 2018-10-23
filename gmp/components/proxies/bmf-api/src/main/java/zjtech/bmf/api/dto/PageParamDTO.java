package zjtech.bmf.api.dto;

import zjtech.bmf.api.constant.ValueConstants;
import zjtech.common.result.BaseDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PageParamDTO extends BaseDTO {
  private int currentPage = ValueConstants.DEFAULT_PAGE; //当前页
  private int pageSize = ValueConstants.DEFAULT_PAGE_SIZE;   //每页显示的条数
  private List<Order> orders = new ArrayList();

  public PageParamDTO(int currentPage, int pageSize) {
    this.currentPage = currentPage;
    this.pageSize = pageSize;
  }

  public PageParamDTO(Integer currentPage, Integer pageSize) {
    if (currentPage != null) {
      this.currentPage = currentPage;
    }
    if (pageSize != null) {
      this.pageSize = pageSize;
    }
  }

  public PageParamDTO(int currentPage, int pageSize, List<Order> orders) {
    this.currentPage = currentPage;
    this.pageSize = pageSize;
    this.orders = orders;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public PageParamDTO addOrder(Order order) {
    this.orders.add(order);
    return this;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public Iterator<Order> iterator() {
    return orders.iterator();
  }

  public static class Order {
    private String property;
    private Direction direction;

    public Order(String property, Direction direction) {
      this.property = property;
      this.direction = direction;
    }

    public String getProperty() {
      return property;
    }

    public void setProperty(String property) {
      this.property = property;
    }

    public Direction getDirection() {
      return direction;
    }

    public void setDirection(Direction direction) {
      this.direction = direction;
    }
  }

  public enum Direction {
    ASC,
    DESC
  }

}
