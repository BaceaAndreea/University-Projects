#pragma once

#include <string>
#include <vector>

using namespace std;

namespace Domain {

    class Electric_scooter{

    private:

        string identifier;
        string model;
        string commissioning_date;
        int kilometer;
        string location;
        string condition;

    public:

        Electric_scooter(string scooter_identifier, string scooter_model, string scooter_commissioning_date, int scooter_kilometer, string scooter_location, string scooter_condition);

        string get_identifier();

        string get_model();

        string get_commissioning_date();

        int get_kilometer() const;

        string get_location();

        string get_condition();

        void set_identifier(string& scooter_identifier);

        void set_model(string& scooter_model);

        void set_commissioning_date(string& scooter_commissioning_date);

        void set_location(string& scooter_location);

        void set_condition(string& scooter_condition);

        void set_kilometer(int& scooter_kilometer);

    };

}

