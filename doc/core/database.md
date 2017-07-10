# 数据库相关技术

- ### 数据库选择
  - 采用couchbase server, couchbase lite以及sync gatewate

- ### 同步相关问题
  - **数据部分同步:** 不是所有的用户都需要同步所有的数据，他们只需要同步他所需要的数据。此处可采用**channel**技术，所有的document都属于一个或多个channel，用户可选择同步一个或者多个channel，可用userId来作为channel的标识符。
