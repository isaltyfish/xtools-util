package net.verytools.util.model;

import net.verytools.util.StrUtil;

public class IndexSafeArray {

    private int len;
    private String[] splits;

    public IndexSafeArray(String[] splits, boolean skipBlank) {
        this.splits = splits;
        this.len = splits.length;
        if (skipBlank) {
            this.len = 0;
            String[] tmp = new String[splits.length];
            for (int i = 0, j = 0, n = splits.length; i < n; i++) {
                if (!StrUtil.isBlank(splits[i])) {
                    tmp[j++] = splits[i];
                    this.len = j;
                }
            }
            this.splits = tmp;
        }
    }

    public String get(int index) {
        if (index >= this.splits.length) {
            return null;
        }
        return this.splits[index];
    }

    public String get(int index, String defaultVal) {
        String ret = get(index);
        return StrUtil.isBlank(ret) ? defaultVal : ret;
    }

    public int len() {
        return this.len;
    }

}
