package service;

import domain.BoardVO;
import mapper.ViewMapper;

public class ViewServiceImpl implements ViewService{

	@Override
	public BoardVO read(BoardVO vo) {
		// TODO Auto-generated method stub
		return new ViewMapper().read(vo);
	}

	

	

	

}
