package com.imooc.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;

@Slf4j
public class ListUtil {
    /**
     * Is list empty?
     *
     * @param list
     * @return
     */
    public static boolean isEmptyList(List<?> list) {
        if (list != null && 0 < list.size()) {
            return false;
        }
        return true;
    }

    /**
     * get list size
     *
     * @param list
     * @return
     */
    public static int getListSize(List<?> list) {
        return null == list ? 0 : list.size();
    }

    /**
     * Merge lists
     *
     * @param list
     * @param lists
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> List<T> mergeList(List<T> list, List<T>... lists) {
        List<T> ret = new ArrayList<>();
        if (!isEmptyList(list)) {
            ret.addAll(list);
        }
        for (List<T> listElement : lists) {
            if (!isEmptyList(listElement)) {
                ret.addAll(listElement);
            }
        }
        return ret;
    }

    @SafeVarargs
    public static <T> List<T> mergeList(T element, List<T>... lists) {
        List<T> ret = new ArrayList<>();
        ret.add(element);
        return mergeList(ret, lists);
    }

    public static <T> List<T> removeDuplicate(List<T> list) {
        HashSet h = new HashSet(list);
        List<T> ret = new ArrayList<>(h);
        return ret;
    }

    /**
     * Convert list to String
     *
     * @param list
     * @param separator
     * @param append
     * @return
     */
    public static String convertListToString(List<String> list, String separator, boolean append) {
        if (ListUtil.isEmptyList(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
            sb.append(separator);
        }
        if (!append) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static <T, K> Map<K, List<T>> makeMapList(List<T> list, Function<T, K> function) {
        Map<K, List<T>> result = new HashMap<>();
        if(list == null){
            return result;
        }
        try {
            for (T item : list) {
                K key = function.apply(item);
                if (!result.containsKey(key)) {
                    result.put(key, new ArrayList<>());
                }
                result.get(key).add(item);
            }
        } catch (Exception e) {
            System.out.println("error makeMap from list, src: "+ e);
        }
        return result;
    }

    public static <T, K> Map<K, List<T>> makeLinkedMapList(List<T> list, Function<T, K> function) {
        Map<K, List<T>> result = new LinkedHashMap<>();
        if(list == null){
            return result;
        }
        try {
            for (T item : list) {
                K key = function.apply(item);
                if (!result.containsKey(key)) {
                    result.put(key, new ArrayList<>());
                }
                result.get(key).add(item);
            }
        } catch (Exception e) {
            System.out.println("error makeMap from list, src: "+ e);
        }
        return result;
    }

    public static <T, K> Map<K, T> makeMap(List<T> list, Function<T, K> function) {
        Map<K, T> result = new HashMap<>(list.size());
        if(list == null){
            return result;
        }
        try {
            for (T item : list) {
                K key = function.apply(item);
                if (!result.containsKey(key)) {
                    result.put(key, item);
                } else {
                    System.out.println("multiplay item found, key: "+key);
                }
            }
        } catch (Exception e) {
            System.out.println("error makeMap from list, src: "+ e);
        }
        return result;
    }

    public static <T, K, V> Map<K, T> makeMapWithCondition(List<T> list, Function<T, K> function, Function<T, V> function2, V target) {
        Map<K, T> result = new HashMap<>(list.size());
        if(list == null){
            return result;
        }
        try {
            for (T item : list) {
                K key = function.apply(item);
                V left = function2.apply(item);
                if(!left.equals(target)){
                    continue;
                }
                if (!result.containsKey(key)) {
                    result.put(key, item);
                } else {
                    System.out.println("multiplay item found, key: "+key);
                }
            }
        } catch (Exception e) {
            System.out.println("error makeMap from list, src: "+ e);
        }
        return result;
    }

    public static <T, K, V> Map<String, T> makeMap(List<T> list, Function<T, K> func1, Function<T, V> func2, String linkChar) {
        Map<String, T> result = new HashMap<>(list.size());
        if(list == null){
            return result;
        }
        try {
            for (T item : list) {
                K key1 = func1.apply(item);
                V key2 = func2.apply(item);
                String key = MessageFormat.format("{0}{1}{2}", key1, linkChar, key2);
                if (!result.containsKey(key)) {
                    result.put(key, item);
                } else {
                    System.out.println("multiplay item found, key: "+key);
                }
            }
        } catch (Exception e) {
            System.out.println("error makeMap from list, src: "+ e);
        }
        return result;
    }

    public static <T, K, V> Map<T, Map<K, V>> makeMapMap(List<V> list, Function<V, T> function1, Function<V, K> function2) {
        Map<T, Map<K, V>> result = new LinkedHashMap<>();
        if(list == null){
            return result;
        }
        try {
            for (V item : list) {
                T key = function1.apply(item);
                if (!result.containsKey(key)) {
                    result.put(key, new HashMap<>());
                }
                K subKey = function2.apply(item);
                if (!result.get(key).containsKey(subKey)) {
                    result.get(key).put(subKey, item);
                } else {
                    System.out.println("multiplay item found, key: "+key+", subKey:"+ subKey);
                }
            }
        } catch (Exception e) {
            System.out.println("error makeMap from list, src: "+ e);
        }
        return result;
    }

    public static <T, K, V> Map<T, Map<K, List<V>>> makeMapMapList(List<V> list, Function<V, T> function1, Function<V, K> function2) {
        Map<T, Map<K, List<V>>> result = new LinkedHashMap<>();
        if(list == null){
            return result;
        }
        try {
            for (V item : list) {
                T key = function1.apply(item);
                if (!result.containsKey(key)) {
                    result.put(key, new HashMap<>());
                }
                K subKey = function2.apply(item);
                if (!result.get(key).containsKey(subKey)) {
                    result.get(key).put(subKey, new ArrayList<>());
                }
                result.get(key).get(subKey).add(item);
            }
        } catch (Exception e) {
            System.out.println("error makeMap from list, src: "+ e);
        }
        return result;
    }

    public static <Z, T, K, V> Map<T, Map<K, Map<Z, V>>> makeMapMapMap(List<V> list, Function<V, T> function1,
                                                                       Function<V, K> function2,
                                                                       Function<V, Z> function3) {
        Map<T, Map<K, Map<Z, V>>> result = new LinkedHashMap<>();
        if(list == null){
            return result;
        }
        try {
            for (V item : list) {
                T key = function1.apply(item);
                if (!result.containsKey(key)) {
                    result.put(key, new HashMap<>());
                }
                K subKey = function2.apply(item);
                if (!result.get(key).containsKey(subKey)) {
                    result.get(key).put(subKey, new HashMap<>());
                }
                Z subSubKey = function3.apply(item);
                if (!result.get(key).get(subKey).containsKey(subSubKey)) {
                    result.get(key).get(subKey).put(subSubKey, item);
                } else {
                    System.out.println("multiplay item found, key: "+key+", subKey:"+ subKey);
                }
            }
        } catch (Exception e) {
            System.out.println("error makeMap from list, src: "+ e);
        }
        return result;
    }


    public static <Z, T, K, V> Map<T, Map<K, Map<Z, List<V>>>> makeMapMapMapList(List<V> list, Function<V, T> function1,
                                                                                 Function<V, K> function2,
                                                                                 Function<V, Z> function3) {
        Map<T, Map<K, Map<Z, List<V>>>> result = new LinkedHashMap<>();
        if(list == null){
            return result;
        }
        try {
            for (V item : list) {
                T key = function1.apply(item);
                if (!result.containsKey(key)) {
                    result.put(key, new HashMap<>());
                }
                K subKey = function2.apply(item);
                if (!result.get(key).containsKey(subKey)) {
                    result.get(key).put(subKey, new HashMap<>());
                }
                Z subSubKey = function3.apply(item);
                if (!result.get(key).get(subKey).containsKey(subSubKey)) {
                    result.get(key).get(subKey).put(subSubKey, new ArrayList<>());
                }
                result.get(key).get(subKey).get(subSubKey).add(item);
            }
        } catch (Exception e) {
            System.out.println("error makeMap from list, src: "+ e);
        }
        return result;
    }

    /**
     * list拆分用于分批插入数据
     * Update By Lu,Zhihong on 2019-11-12
     *
     * @param list 集合
     * @param len  拆分后每个集合的长度
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> list, int len) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        int size = list.size();
        int count = (size + len - 1) / len;
        List<List<T>> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }

}
