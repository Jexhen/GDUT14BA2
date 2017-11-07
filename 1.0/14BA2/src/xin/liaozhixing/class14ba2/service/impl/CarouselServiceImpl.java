package xin.liaozhixing.class14ba2.service.impl;

import java.io.File;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import xin.liaozhixing.class14ba2.dao.CarouselDao;
import xin.liaozhixing.class14ba2.domain.Carousel;
import xin.liaozhixing.class14ba2.service.CarouselService;

public class CarouselServiceImpl implements CarouselService {
	private CarouselDao carouselDao;

	public void setCarouselDao(CarouselDao carouselDao) {
		this.carouselDao = carouselDao;
	}

	@Override
	public List<Carousel> getCarousels() {
		return carouselDao.getCarousels();
	}

	@Override
	public Carousel getCarouselByPrior(Integer prior) {
		return carouselDao.getCarouselByPrior(prior);
	}

	@Override
	public void updateCarousel(Carousel crs) {
		if (crs!=null)
			carouselDao.updateCarousel(crs);
	}

	@Override
	public Integer getMaxPrior() {
		return carouselDao.getMaxPrior();
	}

	@Override
	public void delCarousel(String crs_id) {
		if (crs_id!=null) {
			Carousel crs = carouselDao.getCarouselByCrs_id(Integer.valueOf(crs_id));
			String realPath = ServletActionContext.getServletContext().getRealPath(crs.getCrs_src());
			File file = new File(realPath);
			if (file.exists())
				file.delete();
			carouselDao.delCarousel(crs);
		}
	}

	@Override
	public void saveCarousel(Carousel carousel) {
		if (carousel!=null)
			carouselDao.saveCarousel(carousel);
	}

	@Override
	public Carousel getCarouelByCrs_id(String crs_id) {
		if (crs_id!=null&&!crs_id.isEmpty())
			return carouselDao.getCarouselByCrs_id(Integer.valueOf(crs_id));
		return null;
	}
}
