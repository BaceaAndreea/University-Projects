cmake_minimum_required(VERSION 3.24)
project(Matrix2)

set(CMAKE_CXX_STANDARD 17)

add_executable(Matrix2 ExtendedTest.cpp Matrix.cpp ShortTest.cpp App.cpp "MatrixIterator .h" MatrixIterator.cpp)
