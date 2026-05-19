package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.enums.Character;
import com.example.demo.enums.Region;
import com.example.demo.mapper.OrderDetailMapper;

@Service
public class CharacterService {

	private final OrderDetailMapper orderDetailMapper;

	public CharacterService(OrderDetailMapper orderDetailMapper) {
		this.orderDetailMapper = orderDetailMapper;
	}

	public String getCharacterImageByRegion(Region region) {
		int count = orderDetailMapper.countByRegionId(region.getRegion_id());

		Character character = region.getCharaImage();

		if (count >= 10) {
			return character.getCharaImage3();
		} else if (count >= 5) {
			return character.getCharaImage2();
		} else {
			return character.getCharaImage();
		}
	}

	public int getOrderDetailCountByRegion(Region region) {
		return orderDetailMapper.countByRegionId(region.getRegion_id());
	}
}