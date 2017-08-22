package com.shadow.util;

import java.util.List;

/**
 * Datatables的返回参数
 *
 * @author yy
 * @version 2016/12/21 12:34
 */
public class DatatablesResult<E> {
    private int draw;
    private int recordsTotal;//数据库里总共记录数
    private int recordsFiltered;//如果有接收到前台的过滤条件，则返回的是过滤后的记录数
    private List<E> data;


}
