package com.bawei.entity;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：冷瞳<p>
 * <p>创建时间：2019/12/31<p>
 * <p>更改时间：2019/12/31<p>
 * <p>版本号：1<p>
 */
public class StudentEntity {

    private List<RankingBean> ranking;

    public List<RankingBean> getRanking() {
        return ranking;
    }

    public void setRanking(List<RankingBean> ranking) {
        this.ranking = ranking;
    }

    public static class RankingBean {
        /**
         * avatar : http://blog.zhaoliang5156.cn/api/images/head_zhu_2019_08_01.jpeg
         * name : Alice
         * rank : 1
         */

        private String avatar;
        private String name;
        private String rank;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }
    }
}
