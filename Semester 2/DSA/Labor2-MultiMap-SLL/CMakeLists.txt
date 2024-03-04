cmake_minimum_required(VERSION 3.24)
project(MultiMap)

set(CMAKE_CXX_STANDARD 20)

add_executable(MultiMap MultiMap.cpp ExtendedTest.h ExtendedTest.cpp App.cpp MultiMapIterator.cpp ShortTest.cpp)
