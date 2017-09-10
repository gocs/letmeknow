using letmeknow_admin.Models;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace letmeknow_admin.Services
{
    class ComplaintService
    {
        private static string attr = GeneralSetting.host + "whistleblowings/";

        public static ObservableCollection<Complaint> getAllComplaints()
        {
            string JsonString = HttpHelper.Get(attr + "allWhistleBlowing");
            return new ObservableCollection<Complaint>(JsonHelper.DeserializeJsonToList<Complaint>(JsonString));
        }

        public static void closeComplaint(Complaint complaint)
        {
            HttpHelper.Put(attr + "closeWhistleBlowing/" + complaint.id);
        }
    }
}
