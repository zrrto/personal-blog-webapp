/*
 * Copyright (C) 2019 the original author or authors.
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

package com.zuoxiaolong.blog.client;

import com.zuoxiaolong.blog.entity.Tag;

import java.util.List;
import java.util.Map;

/**
 * @author xiaolongzuo
 */
public interface TagDubboService {

    List<Map<String, Object>> getHotTags();

    Integer save(String tagName);

    Integer getId(String tagName);

    boolean deleteByArticleId(Integer articleId);

    List<Map<String, Object>> getTags(Integer articleId);

}
