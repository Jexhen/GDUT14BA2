package xin.liaozhixing.class14ba2.dao;

import java.util.List;

import xin.liaozhixing.class14ba2.domain.Carousel;

public interface CarouselDao {

	List<Carousel> getCarousels();

	Carousel getCarouselByPrior(Integer prior);

	void updateCarousel(Carousel crs);

	Integer getMaxPrior();

	void delCarousel(Carousel crs);

	Carousel getCarouselByCrs_id(Integer crs_id);

	void saveCarousel(Carousel carousel);

}
