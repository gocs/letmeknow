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
    public partial class ApplicationManager : MetroWindow
    {
        private ObservableCollection<Models.Application> applicationList;

        public ApplicationManager()
        {
            InitializeComponent();
            applicationList = ApplicationService.getAllApplications();
            dataGrid.ItemsSource = applicationList;
        }

        private void dataGrid_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            var selectedItem = ((DataGrid)sender).SelectedItem as Models.Application;
            if (selectedItem == null)
            {
                textBlock.Text = "";
                return;
            }
            textBlock.Text = selectedItem.content;
        }

        private void tileReject_Click(object sender, RoutedEventArgs e)
        {
            if (dataGrid.SelectedItem == null) return;
            ApplicationService.rejectApplication(dataGrid.SelectedItem as Models.Application);
            applicationList.Remove(dataGrid.SelectedItem as Models.Application);
        }

        private void tileApprove_Click(object sender, RoutedEventArgs e)
        {
            if (dataGrid.SelectedItem == null) return;
            ApplicationService.approveApplication(dataGrid.SelectedItem as Models.Application);
            applicationList.Remove(dataGrid.SelectedItem as Models.Application);
        }

        private void tileDetail_Click(object sender, RoutedEventArgs e)
        {
            Models.Application selectedItem = dataGrid.SelectedItem as Models.Application;
            if (selectedItem == null) return;
            GroupDetail groupDetail = new GroupDetail((int)selectedItem.groupId);
            groupDetail.Show();
        }
    }
}
