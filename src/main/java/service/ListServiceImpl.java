package service;

import java.util.Collection;

import domain.BoardInfo;
import domain.BoardVO;

import mapper.ListMapper;

public class ListServiceImpl implements ListService {

	@Override
	public Collection<BoardVO> read() {
		// TODO Auto-generated method stub
		return new ListMapper().read();
	}

	public int totalRow() {
		// TODO Auto-generated method stub
		return new ListMapper().totalRow();
	}

	//
	public BoardInfo boardInfo() {
		BoardInfo boardInfo = new BoardInfo();
		boardInfo.setList(new ListMapper().read());
		boardInfo.setTotalRow(new ListMapper().totalRow());
		return boardInfo;
	}

	//검색 1
	public BoardInfo boardInfo(int startPage, int pageRow, String field, String keyWord) {
		BoardInfo boardInfo = new BoardInfo();
		boardInfo.setList(
				new ListMapper().read(startPage, pageRow, field, keyWord));
		boardInfo.setTotalRow(new ListMapper().totalRow(keyWord));
		return boardInfo;
	}

	//검색 2
	public BoardInfo boardInfo(int startPage, int pageRow, String field1, String keyWord1,	String keyWord2, String keyWord3) {
		BoardInfo boardInfo = new BoardInfo();
		boardInfo.setList(
				new ListMapper().read1(startPage, pageRow, field1, keyWord1, keyWord2, keyWord3));
		boardInfo.setTotalRow(new ListMapper().totalRow1( keyWord1, keyWord2, keyWord3));
		return boardInfo;
	}

}
