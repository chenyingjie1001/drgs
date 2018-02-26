package com.firesoon.drgs.dto.base;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class ResultMessage<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 579983520800885005L;

	private List<T> data;
	private int httpCode = 200;
	private String msg = "Success";
	private List<Map<String, Object>> mapdata;
	private T t;
	private Map<String, Object> m;
	private PageInfo<T> pageinfo;
	private Date timestamp;
	private Object col;
	private String url;
}
