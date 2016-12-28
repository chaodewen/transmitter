# mdsp-transmitter

Transmitter of the Medical Data Security Platform.

### 在HTTPS上还使用HMAC的原因

1. 防止无效请求攻击：因为公钥是公开的，只使用HTTPS无法确定请求来源是否合法，HMAC散列可以保证请求合法性；

2. 实际项目部署一般是Client ---HTTPS---Load Balance ---HTTP---HTTP Server，负载均衡设备到服务器之间使用HTTP传输，虽然是内部安全域，但是仍然有安全隐患，因此需要应用层安全保障。