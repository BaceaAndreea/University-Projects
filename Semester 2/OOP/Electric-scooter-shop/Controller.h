#pragma once
#include "InMemory_Repository.h"
#include "CSV_Repository.h"
#include <memory>

using namespace Repository;

namespace Controller {

    class Electric_scooter_Controller{

    private:

        shared_ptr <CRUD_Repo> Scooter_Repo;

    public:

        void InMemory();

        void add_InMemory();

        void CSV();

        void add_CSV();

        void add(string scooter_identifier, string scooter_model, string scooter_commissioning_date, int scooter_kilometer, string scooter_location, string scooter_condition);

        void del(string scooter_identifier);

        void edit(string scooter_identifier, string scooter_model, string scooter_commissioning_date, int scooter_kilometer, string scooter_location, string scooter_condition);

        vector <Electric_scooter> search_by_location(const string& scooter_location);

        vector <Electric_scooter> filter_by_commissioning_date(const string& scooter_commissioning_date);

        vector <Electric_scooter> filter_by_kilometer(int scooter_kilometer);

        vector <Electric_scooter> sort_by_commissioning_date();

        vector <Electric_scooter> get_all();

        void modify_condition(const string& scooter_identifier);

        void reserve_scooter(const string& scooter_identifier);

        vector <Electric_scooter> see_reserved_scooters();

        bool exists(const string& scooter_identifier);

    };

}