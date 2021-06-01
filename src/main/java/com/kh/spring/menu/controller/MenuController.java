package com.kh.spring.menu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.menu.model.service.MenuService;
import com.kh.spring.menu.model.vo.Menu;

import lombok.extern.slf4j.Slf4j;
/**
 * @Controller + @ResponseBody
 * 
 * 모든 handler는 = @ResponseBody로 처리된다.
 *
 */
@RestController
@Slf4j
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@GetMapping("/menus")
	public List<Menu> menus(){
		//1. 업무로직
		List<Menu> list;
		try {
			list = menuService.selectMenuList();
			log.debug("list = {}",list);
			
			//2. 리턴 @ResponseBody에 의해서 json변환후, 응답처리 출력
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("/menus 오류!", e);
			throw e;
		}
	}
	
	/**
	 * @PathVariable 경로변수
	 * 
	 * @return
	 */
	@GetMapping("/menus/{type}/{taste}")
	public List<Menu> menuByType(@PathVariable String type, @PathVariable String taste){
		try {
			log.debug("type = {}, taste = {}",type,taste);
			Map<String, Object> param = new HashMap<>();
			param.put("type", type);
			param.put("taste", taste);
			
			List<Menu> list = menuService.selectMenuListByTypeAndTaste(param);
			log.debug("list = {}", list);
			
			return list;
		} catch (Exception e) {
			log.error("/menus/{type} 오류!", e);
			throw e;
		}
	}
	
	/**
	 * @RequestBody 요청메세지의 body에 작성된 json 문자열을 java객체로 변환
	 * @param menu
	 * @return
	 */
	
	@PostMapping("/menu")
	public Map<String, Object> insertMenu(
			@RequestBody Menu menu
			){
		try {
			log.debug("menu = {}", menu);
			int result = menuService.insertMenu(menu);
			
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "메뉴 등록 성공!");
			
			return map;
		} catch (Exception e) {
			log.error("메뉴 등록 실패!",e);
			throw e;
		}
	}
	
	@GetMapping("/menu/{id}")
	public Menu searchMenu(@PathVariable String id) {
		try {
			Menu menu = menuService.selectMenu(id);
			//log.debug("menu = {}",menu);
			
			return menu;
		} catch (Exception e) {
			log.error("메뉴 검색 실패!",e);
			throw e;
		}
	}
	@PutMapping("/menu")
	public Map<String, Object> updateMenu(@RequestBody Menu menu){
		try {
			log.debug("menu = {}", menu);
			int result = menuService.updateMenu(menu);
			
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "메뉴 수정 성공!");
			
			return map;
		} catch (Exception e) {
			log.error("메뉴 등록 실패!",e);
			throw e;
		}
	}

}
