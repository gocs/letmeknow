using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using MahApps.Metro.Controls;
using letmeknow_admin.Models;
using System.Collections.ObjectModel;
using letmeknow_admin.Services;

namespace letmeknow_admin
{
    /// <summary>
    /// ComplaintManager.xaml 的交互逻辑
    /// </summary>
    public partial class ComplaintManager : MetroWindow
    {
        private ObservableCollection<Complaint> complaintList;

        public ComplaintManager()
        {
            InitializeComponent();
            complaintList = ComplaintService.getAllComplaints();
            foreach (var i in complaintList)
            {
                switch (i.category)
                {
                    case ComplaintCategory.GROUP:
                        i.categoryName = "通知组";
                        break;
                    case ComplaintCategory.NOTIFICATION:
                        i.categoryName = "通知";
                        break;
                    case ComplaintCategory.USER:
                        i.categoryName = "用户";
                        break;
                }
                i.whistleblowerName = UserService.getUser(i.whistleblowerId).username;
            }
            dataGrid.ItemsSource = complaintList;
        }

        private void dataGrid_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Complaint selectedItem = ((DataGrid)sender).SelectedItem as Complaint;
            if (selectedItem == null)
            {
                lblName.Content = "";
                textBlock.Text = "";
                return;
            }
            switch (selectedItem.category)
            {
                case ComplaintCategory.GROUP:
                    lblName.Content = string.Format("通知组\"{0}\"", selectedItem.groupName);
                    break;
                case ComplaintCategory.NOTIFICATION:
                    lblName.Content = string.Format("由{0}发送的通知", selectedItem.ravenSender);
                    break;
                case ComplaintCategory.USER:
                    lblName.Content = string.Format("用户\"{0}\"", selectedItem.username);
                    break;
            }
            textBlock.Text = selectedItem.description;
        }

        private void tileClose_Click(object sender, RoutedEventArgs e)
        {
            if (dataGrid.SelectedItem == null) return;
            ComplaintService.closeComplaint(dataGrid.SelectedItem as Complaint);
            complaintList.Remove(dataGrid.SelectedItem as Complaint);
        }

        private void tileDetail_Click(object sender, RoutedEventArgs e)
        {
            Complaint selectedItem = dataGrid.SelectedItem as Complaint;
            if (selectedItem == null) return;
            switch (selectedItem.category)
            {
                case ComplaintCategory.GROUP:
                    GroupDetail groupDetail = new GroupDetail(selectedItem.groupId);
                    groupDetail.Show();
                    break;
                case ComplaintCategory.NOTIFICATION:
                    var t = NotificationService.GetNotificationById(selectedItem.ravenId);
                    NotificationList notificationList = new NotificationList("通知详情", t);
                    notificationList.Show();
                    break;
                case ComplaintCategory.USER:
                    UserDetail userDetail = new UserDetail(selectedItem.userId);
                    userDetail.Show();
                    break;
            }
        }
    }
}
