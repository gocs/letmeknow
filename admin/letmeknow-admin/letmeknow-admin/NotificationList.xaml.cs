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
                dataGridOption.ItemsSource = null;
            }
            else
            {
                lblSender.Content = UserService.getUser(notification.addresserId).username;
                lblGroup.Content = GroupService.getGroup(notification.houseId).name;
                lblTime.Content = notification.createdDateString;
                NotificationContent.Text = notification.description;
                dataGridOption.ItemsSource = notification.optionPolls;
            }
        }

        private void tileSender_Click(object sender, RoutedEventArgs e)
        {
            var notification = dataGrid.SelectedItem as Notification;
            if (notification == null) return;
            var userDetail = new UserDetail(notification.addresserId);
            userDetail.Show();
        }

        private void tileDelete_Click(object sender, RoutedEventArgs e)
        {
            var notification = dataGrid.SelectedItem as Notification;
            if (notification == null) return;
            MessageBoxResult r = MessageBox.Show("是否确认删除此条通知？", "删除确认", MessageBoxButton.YesNo, MessageBoxImage.Question);
            if (r == MessageBoxResult.No) return;
            NotificationService.deleteNotification(notification);
            dataList.Remove(notification);
        }

        private void tileGroup_Click(object sender, RoutedEventArgs e)
        {
            var notification = dataGrid.SelectedItem as Notification;
            if (notification == null) return;
            var groupDetail = new GroupDetail(notification.houseId);
            groupDetail.Show();
        }
    }
}
