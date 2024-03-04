#pragma once
#include "Repository.h"

namespace Repository{

    class CSV_Repo: public CRUD_Repo{

    private:

        vector <Electric_scooter> Electric_scooters;

        void write();

    public:

        void add(Electric_scooter new_scooter) override;

        void del(const string& deleted_scooter_identifier) override;

        void edit(Electric_scooter edited_scooter) override;

        vector <Electric_scooter> get_all() override;


    };

}