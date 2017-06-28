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

namespace letmeknow_admin
{
    /// <summary>
    /// NotificationList.xaml 的交互逻辑
    /// </summary>
    public partial class NotificationList : MetroWindow
    {
        private ObservableCollection<Notification> dataList;

        public NotificationList(string title, ObservableCollection<Notification> SearchResult)
        {
            InitializeComponent();
            dataGrid.ItemsSource = SearchResult;
            dataList = SearchResult;
            lblTitle.Content = title;
        }

        private void dataGrid_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            var notification = (sender as DataGrid).SelectedItem as Notification;
            if (notification == null)
            {
                lblSender.Content = "";
                lblGroup.Content = "";
                lblTime.Content = "";
                NotificationContent.Text = "";
            }
            else
            {
                lblSender.Content = notification.user_name;
                lblGroup.Content = notification.group_name;
                lblTime.Content = notification.time;
                NotificationContent.Text = notification.content;
            }
        }

        private void tileSender_Click(object sender, RoutedEventArgs e)
        {
            var notification = dataGrid.SelectedItem as Notification;
            if (notification == null) return;
            var userDetail = new UserDetail(notification.user_id);
            userDetail.Show();
        }

        private void tileDelete_Click(object sender, RoutedEventArgs e)
        {
            if (dataGrid.SelectedIndex == -1) return;
            dataList.RemoveAt(dataGrid.SelectedIndex);
        }
    }
}
