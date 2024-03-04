#pragma once
#include "Domain.h"
#include <vector>

using namespace std;
using namespace Domain;

namespace Repository{


    class CRUD_Repo{

    public:

        virtual void add(Electric_scooter new_scooter) = 0;

        virtual void del(const string& deleted_scooter_identifier) = 0;

        virtual void edit(Electric_scooter edited_scooter) = 0;

        virtual vector <Electric_scooter> get_all() = 0;

    };

}
