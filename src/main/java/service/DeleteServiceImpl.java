package service;



import mapper.deleteMapper;

public class DeleteServiceImpl implements DeleteService{

	@Override
	public void delete(int num) {
			new deleteMapper().delete(num);
		
	}

	
		
}
