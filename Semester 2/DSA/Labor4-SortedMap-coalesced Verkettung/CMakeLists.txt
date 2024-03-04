cmake_minimum_required(VERSION 3.24)
project(SortedMap)

set(CMAKE_CXX_STANDARD 20)

add_executable(SortedMap ExtendedTest.cpp ShortTest.cpp SMIterator.cpp SortedMap.cpp App.cpp)
