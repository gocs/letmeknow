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
        public static ObservableCollection<Complaint> getAllComplaints()
        {
            string JsonString = HttpHelper.Get(GeneralSetting.host + "allComplaints", string.Empty);
            return JsonHelper.DeserializeJsonToObject<Dictionary<string, ObservableCollection<Complaint>>>(JsonString)["complaints"];
        }

        public static void closeComplaint(Complaint complaint)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["reportId"] = complaint.reportId.ToString();
            HttpHelper.Post(GeneralSetting.host + "closeComplaint", parameters);
        }
    }
}
