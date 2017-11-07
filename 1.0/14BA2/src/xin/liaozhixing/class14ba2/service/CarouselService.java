package xin.liaozhixing.class14ba2.service;

import java.util.List;

import xin.liaozhixing.class14ba2.domain.Carousel;

public interface CarouselService {

	List<Carousel> getCarousels();

	Carousel getCarouselByPrior(Integer prior);

	void updateCarousel(Carousel crs);

	Integer getMaxPrior();

	void delCarousel(String crs_id);

	void saveCarousel(Carousel carousel);

	Carousel getCarouelByCrs_id(String crs_id);

}
