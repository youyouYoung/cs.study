package fibonacci;

/**
 * 功能描述: fibonacci公式的实现策略
 * fibonacci公式: f(n) = f(n-1) + f(n-2)
 * 其中:
 * f(0) = 0
 * f(1) = 1
 *
 * @author youyou
 * @date 11/10/19 8:59 PM
 */
interface FibonacciStrategy {

    /**
     * 功能描述: 计算数值n的fibonacci函数结果
     *
     * @param n 待求值的自变量
     * @return fibonacci函数值
     * @author youyou
     * @date 11/10/19 9:00 PM
     */
    long fibonacci(int n);
}
