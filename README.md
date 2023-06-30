# BufferedInputStream을 사용하는 이유
BufferedInputStream은 InputStream의 read 메소드를 최적화하는 역할을 한다. InputStream의 read는 호출될 때마다 매번 Disk에 접근하여 1Byte를 읽어드린다. BufferedInputStream은 디폴트 값인 8byte씩 데이터를 읽어 버퍼에 저장한 후(배열에 저장함) 1Byte만 반환하고 이후 read를 호출할 때 버퍼에서 우선적으로 가져온다. Disk에 접근하는 것은 매우 비싼 작업이기 때문에 이러한 작업의 횟수를 줄여 속도에 도움을 주는 방법이다.

InputStream을 BufferedInputStream으로 래핑하여 사용한다. OutputStream, Reader와 Writer도 같은 이유로 Buffered를 래핑하여 사용할 수 있다.

## 테스트 목표
BufferedInputStream과 InputStream의 속도 차이를 확인한다.

## 실행
FileInputStream과 FileInputStream을 래핑한 BufferedInputStream 두개의 클래스를 구현하여 read 메소드를 실행하여 약 30mb의 src/anyFile.txt 파일을 전체 1회 읽는다.

## 구현
BufferedInputStream을 구현한 readFileWithBufferedStream과 InputStream만 구현한 readFileWithInputStream 메소드의 cpu time을 비교한다.

## 측정
측정 도구는 VisualVm을 사용, Sampler의 CPU samples의 메소드 별 Total Time을 비교.

Mac m1에서 테스트함.

## 결과
![buffered-vs-inputstream](https://github.com/sunho-lee/Java-InputOutput/assets/27765412/217f9001-8c91-4c79-a175-f4fa37548530)

readFileWithInputStream 메소드는 9,301ms가 소요되었고 readFileWithBufferedStream 메소드는 198ms가 소요됨.

30mb 정도의 데이터는 큰 차이가 발생하였고 30kb정도의 데이터에서는 유의미한 차이는 없는 것으로 보인다.
