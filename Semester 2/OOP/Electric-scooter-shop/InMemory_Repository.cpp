#include "InMemory_Repository.h"
#include <vector>
#include <algorithm>

namespace Repository {

    void InMemory_Repo::add(Electric_scooter new_scooter) {

        Electric_scooters.push_back(new_scooter);

    }

    void InMemory_Repo::del(const string &deleted_scooter_identifier) {

        for (auto scooter = Electric_scooters.begin(); scooter < Electric_scooters.end(); scooter++) {

            if (scooter->get_identifier() == deleted_scooter_identifier) {

                Electric_scooters.erase(scooter);

            }
        }

    }

    void InMemory_Repo::edit(Electric_scooter edited_scooter) {

        del(edited_scooter.get_identifier());
        add(edited_scooter);

    }

    vector<Electric_scooter> InMemory_Repo::get_all() {

        vector<Electric_scooter> scooters;
        for (auto &scooter: Electric_scooters) {

            scooters.push_back(scooter);

        }

        return scooters;

    }

}