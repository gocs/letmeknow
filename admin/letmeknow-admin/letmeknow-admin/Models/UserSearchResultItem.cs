using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace letmeknow_admin.Models
{
    class UserSearchResultItem
    {
        public int UID { get; set; }
        public string name { get; set; }

        public UserSearchResultItem(int UID, string name)
        {
            this.UID = UID;
            this.name = name;
        }
    }
}
