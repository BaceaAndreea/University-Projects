cmake_minimum_required(VERSION 3.24)
project(OOP_lab)

set(CMAKE_CXX_STANDARD 23)

set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/bin)

include_directories(.)


set(CMAKE_AUTOUIC ON)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)

set(CMAKE_CXX_STANDARD 23)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

find_package(Qt5 COMPONENTS Widgets REQUIRED)

add_executable(L5_Teambcc
        main.cpp
        Domain.cpp
        Domain.h
        Controller.cpp
        Controller.h
        Electric_Scooter_details_dialog.h
        Electric_Scooter_details_dialog.cpp
        Electric_scooter_Gui.cpp
        Electric_scooter_Gui.h
        InMemory_Repository.cpp
        InMemory_Repository.h
        Repository.h
        Tests.cpp
        Tests.h
        UI.cpp
        UI.h
        CSV_Repository.cpp
        CSV_Repository.h
        )

target_link_libraries(L5_Teambcc PRIVATE Qt5::Widgets)