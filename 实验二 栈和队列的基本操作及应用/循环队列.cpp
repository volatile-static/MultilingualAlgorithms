#include <cstdlib>
#include <iostream>
#include <vector>

#define GET_MAX(a, b) (a = a > b ? a : b)

template <typename T> class RingBuffer {
    std::vector<T> data;
    size_t head = 0, tail = 0;

  public:
    RingBuffer(size_t size = 1) : data(size) {
        if (!size)
            throw "缓冲区不能为空！";
    }
    ~RingBuffer() {}

    inline void put(T value) { data[++tail %= data.size()] = value; }

    inline void pop() { ++head %= data.size(); }

    inline T get() { return data[(head + 1) % data.size()]; }

    inline size_t size() { return (tail - head) % data.size(); }
};

int main() {
    std::srand(233333);
    RingBuffer<int> q(666);
    int maxLen(0);

    for (int i(0); i < 50; ++i) {
        q.put(std::rand());
        q.put(std::rand());
        std::cout << q.get() << ' ';
        GET_MAX(maxLen, q.size());
        q.pop();
    }
    std::cout << "\n================================\n";
    while (q.size()) {
        std::cout << q.get() << ' ';
        q.pop();
    }
    std::cout << "\nmax len = " << maxLen << std::endl;
    return 0;
}
