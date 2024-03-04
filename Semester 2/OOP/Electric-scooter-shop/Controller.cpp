#include "Controller.h"
#include <fstream>
#include <algorithm>

using namespace Controller;

void Electric_scooter_Controller::InMemory(){

    Scooter_Repo = make_shared<InMemory_Repo>();

}

void Electric_scooter_Controller::add_InMemory() {

    add("XXX", "X6", "20220302", 15002, "Berlin", "reserved");
    add("MGL", "S10", "20240312", 1, "Dubai", "waiting");
    add("BMW", "E92", "20010322", 1500982, "Slobozia", "parked");
    add("PCY", "Z100", "21001231", 0, "Milano", "waiting");
    add("DCA", "1310", "19801102", 22000002, "Medias", "out-of-use");
    add("TYO", "X69", "20150302", 300002, "Amsterdam", "in-use");
    add("TTO", "Y69", "20180322", 3012002, "Riad", "waiting");
    add("MMM", "MM66", "20290101", 0, "Kiev", "waiting");
    add("ZZZ", "Z01", "19990406", 1500000, "Amsterdam", "in-use");
    add("OLD", "01", "18000305", 199922291, "Hamburg", "out-of-use");


}

void Electric_scooter_Controller::CSV() {

    Scooter_Repo = make_shared<CSV_Repo>();

}

void Electric_scooter_Controller::add_CSV() {

    ifstream f("scooters.txt");
    string scooter_identifier, scooter_model, scooter_commissioning_date;
    int scooter_kilometer;
    string scooter_location,scooter_condition;
    while(!f.eof()) {
        f >> scooter_identifier >> scooter_model >> scooter_commissioning_date >> scooter_kilometer >> scooter_location >> scooter_condition;
        add(scooter_identifier, scooter_model, scooter_commissioning_date, scooter_kilometer, scooter_location, scooter_condition);
    }
    f.close();

}

void Electric_scooter_Controller::add(string scooter_identifier, string scooter_model, string scooter_commissioning_date, int scooter_kilometer, string scooter_location, string scooter_condition) {

    Electric_scooter new_scooter(scooter_identifier, scooter_model, scooter_commissioning_date, scooter_kilometer, scooter_location,scooter_condition);

    if(!exists(new_scooter.get_identifier())){

        Scooter_Repo->add(new_scooter);

    }
}

void Electric_scooter_Controller::del(string scooter_identifier) {

    if(exists(scooter_identifier)){

        Scooter_Repo->del(scooter_identifier);

    }

}

void Electric_scooter_Controller::edit(string scooter_identifier, string scooter_model,string scooter_commissioning_date, int scooter_kilometer,string scooter_location, string scooter_condition) {

    if(exists(scooter_identifier)){

        Electric_scooter new_scooter(scooter_identifier, scooter_model, scooter_commissioning_date, scooter_kilometer, scooter_location,scooter_condition);
        Scooter_Repo->edit(new_scooter);

    }

}

vector <Electric_scooter> Electric_scooter_Controller::search_by_location(const string &scooter_location) {

    vector <Electric_scooter> scooters;
    for(auto& scooter: Scooter_Repo->get_all()){

        if(scooter.get_location() == scooter_location) {

            scooters.push_back(scooter);

        }

    }

    return scooters;


}


vector <Electric_scooter> Electric_scooter_Controller::filter_by_commissioning_date(const string &scooter_commissioning_date) {

    vector <Electric_scooter> scooters;
    for(auto& scooter: Scooter_Repo->get_all()){

        if(scooter.get_commissioning_date() < scooter_commissioning_date) {

            scooters.push_back(scooter);

        }

    }

    return scooters;

}

vector <Electric_scooter> Electric_scooter_Controller::filter_by_kilometer(int scooter_kilometer) {

    vector <Electric_scooter> scooters;
    for(auto& scooter: Scooter_Repo->get_all()){

        if(scooter.get_kilometer() < scooter_kilometer) {

            scooters.push_back(scooter);

        }

    }

    return scooters;

}

vector <Electric_scooter> Electric_scooter_Controller::sort_by_commissioning_date(){

    vector <Electric_scooter> scooters = get_all();
    sort(scooters.begin(), scooters.end(), [](Electric_scooter scooter1, Electric_scooter scooter2){return stoi(scooter1.get_commissioning_date())<stoi(scooter2.get_commissioning_date());});
    return scooters;

}

vector <Electric_scooter> Electric_scooter_Controller::get_all() {

    vector <Electric_scooter> scooters;
    scooters = Scooter_Repo->get_all();
    return scooters;

}

bool Electric_scooter_Controller::exists(const string& scooter_identifier) {

    for (auto& scooter: Scooter_Repo->get_all()){


        if(scooter_identifier == scooter.get_identifier()){

            return true;

        }
    }

    return false;

}


void Electric_scooter_Controller::modify_condition(const string& scooter_identifier){

    for (auto& scooter: Scooter_Repo->get_all()){

        if(scooter_identifier == scooter.get_identifier()){

            edit(scooter.get_identifier(),scooter.get_model(),scooter.get_commissioning_date(),scooter.get_kilometer(),scooter.get_location(),"in-use");

        }
    }
}

void Electric_scooter_Controller::reserve_scooter(const string &scooter_identifier) {

    for (auto  scooter: Scooter_Repo->get_all()){

        if(scooter_identifier == scooter.get_identifier()){

            edit(scooter.get_identifier(),scooter.get_model(),scooter.get_commissioning_date(),scooter.get_kilometer(),scooter.get_location(),"reserved");

        }
    }
}

vector <Electric_scooter> Electric_scooter_Controller::see_reserved_scooters() {

    vector <Electric_scooter> scooters;

    for (auto& scooter: Scooter_Repo->get_all()){

        if(scooter.get_condition() == "reserved"){

            scooters.push_back(scooter);

        }
    }

    return scooters;

}