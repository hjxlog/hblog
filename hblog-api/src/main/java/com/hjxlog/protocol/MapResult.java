package com.hjxlog.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Huang JX
 * @date: 2023/2/27
 */
public class MapResult<K, V> extends Result<Map<K, V>> {

    public MapResult() {
        super();
        super.setBody(new HashMap<>());
    }

    public MapResult(Map<K, V> map) {
        super(map);
    }

    public MapResult(ResultMessage message) {
        super(message);
    }

    public MapResult(ResultMessage message, Object... args) {
        super(message, args);
    }

}
