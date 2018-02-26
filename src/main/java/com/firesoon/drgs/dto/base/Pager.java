package com.firesoon.drgs.dto.base;

import lombok.Data;

import java.io.Serializable;


@Data
public class Pager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private int pageNum = 1;
	private int pageSize = 10;
}
