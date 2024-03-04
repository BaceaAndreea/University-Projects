#include "CSV_Repository.h"
#include <vector>
#include <algorithm>
#include <fstream>

namespace Repository {

    void CSV_Repo::write() {

        ofstream f;
        f.open("scooters.txt", ofstream::out | ofstream::trunc);//makes the file empty
        for(auto scooter: Electric_scooters)
            f<<scooter.get_identifier()<<" "<<scooter.get_model()<<" "<<scooter.get_commissioning_date()<<" "<<scooter.get_kilometer()<<" "<<scooter.get_location()<<" "<<scooter.get_condition()<<"\n";
        f.close();

    }

    void CSV_Repo::add(Electric_scooter new_scooter) {

        Electric_scooters.push_back(new_scooter);
        write();

    }

    void CSV_Repo::del(const string &deleted_scooter_identifier) {

        for (auto scooter = Electric_scooters.begin(); scooter < Electric_scooters.end(); scooter++) {

            if (scooter->get_identifier() == deleted_scooter_identifier) {

                Electric_scooters.erase(scooter);

            }
        }
        write();

    }

    void CSV_Repo::edit(Electric_scooter edited_scooter) {

        del(edited_scooter.get_identifier());
        add(edited_scooter);
        write();

    }

    vector<Electric_scooter> CSV_Repo::get_all() {

        vector<Electric_scooter> scooters;
        for (auto &scooter: Electric_scooters) {

            scooters.push_back(scooter);

        }

        return scooters;

    }

}