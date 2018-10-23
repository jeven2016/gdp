package zjtech.bmf.api.dto;

import zjtech.common.result.BaseDTO;
import zjtech.common.result.IBaseDTO;

import java.util.ArrayList;
import java.util.List;

public class PageResultDTO<E extends IBaseDTO> extends BaseDTO {
  private int currentPage; //当前页
  private int pageSize;   //每页显示的条数
  private int totalPages;      //共有多少页
  private long totalElements;  //所有的记录条数
  private List<E> elements = new ArrayList<>();

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

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(long totalElements) {
    this.totalElements = totalElements;
  }

  public void setElements(List<E> elements) {
    this.elements = elements;
  }

  public List<E> getElements() {
    return elements;
  }
}
