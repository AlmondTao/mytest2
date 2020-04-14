package com.taoqy.common;

/**
 * For redis sentinel config in application-dev.yml
 *
 * @author taoqy
 */

public class Pool {
    private int maxIdle = 8;

    /**
     * Target for the minimum number of idle connections to maintain in the pool. This
     * setting only has an effect if it is positive.
     */
    private int minIdle = 0;

    /**
     * Max number of connections that can be allocated by the pool at a given time.
     * Use a negative value for no limit.
     */
    private int maxActive = 8;

    /**
     * Maximum amount of time (in milliseconds) a connection allocation should block
     * before throwing an exception when the pool is exhausted. Use a negative value
     * to block indefinitely.
     */
    private int maxWait = -1;

    private boolean testOnBorrow = false;



    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }


}
