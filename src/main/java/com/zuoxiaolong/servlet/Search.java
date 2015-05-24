/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author 左潇龙
 * @since 2015年5月19日 上午1:11:48
 */
package com.zuoxiaolong.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import com.zuoxiaolong.algorithm.Match;
import com.zuoxiaolong.dao.HeroDao;
import com.zuoxiaolong.dao.MatchDao;

/**
 * @author zuoxiaolong
 *
 */
public class Search extends BaseServlet {

	private static final long serialVersionUID = 4998713126399162358L;

	@Override
	protected void service() throws ServletException, IOException {
		HttpServletRequest request = getRequest();
		String[] h = request.getParameter("h").split(",");
		if (h.length != 5) {
			writeText("请填满五个英雄！");
			return;
		}
		SortedSet<String> hSet = new TreeSet<>();
		for (int i = 0 ; i < h.length;i++) {
			if (h[i].trim().length() == 0) {
				writeText("第" + (i + 1) + "位英雄为空");
				return;
			}
			if (hSet.contains(h[i])) {
				writeText("第" + (i + 1) + "位英雄重复");
				return;
			}
			if (!HeroDao.exsits(h[i].trim())) {
				writeText("第" + (i + 1) + "位英雄在英雄库中没找到，请按照提示输入英雄");
				return;
			}
			hSet.add(h[i]);
		}
		String currentBattle = JSONArray.fromObject(hSet).toString();
		if (logger.isInfoEnabled()) {
			logger.info("search servlet's currentBattle is : " + currentBattle);
		}
		List<Map<String,String>> matches = MatchDao.findMatchesResult(currentBattle);
		if (logger.isInfoEnabled()) {
			logger.info("search servlet's matches is : " + matches);
		}
		Map<String, int[]> totalMap = Match.computeBattleCharts(matches, currentBattle);
		Map<String,Object> result = new HashMap<>();
		List<String> orderList = new ArrayList<>();
		Match.fillOrderList(totalMap, orderList);
		
		result.put("totalMap", totalMap);
		result.put("orderList", orderList);
		if (logger.isInfoEnabled()) {
			logger.info("search servlet's result is : " + result);
		}
		writeJsonObject(result);
	}

}
