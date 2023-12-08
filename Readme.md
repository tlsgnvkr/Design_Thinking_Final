전체 구조도\n
MainActivity: 캘린더 노출\n
    fragment_first: 캘린더 전체보기\n
        inner_date: 날짜별 항목\n
    fragment_second: 캘린더 날짜보기\n
        inner_date: 날짜별 항목\n
        date_contentlist: 해당 일자 할 일 목록\n
	* inner date에서 data_dolist에 삽입되는 것만 달라짐\n
-> EditDateActivity: 해당 일자의 일정 수정\n
-> EmailActivity: 이메일 리스트 노출\n
    email_new: 새 이메일 목록\n
    email_storage: 보관 이메일 목록\n
    -> MailContentActivity: 이메일 세부 내용 보기\n

상세설명\n
date_num: 각 날짜별 숫자\n
date_dolist: 해당 날짜에서 할 일 목록\n

fragment_first: 기본 보기\n
fragment_second: 날짜 보기\n
날짜 보기는 Recyclerview로 구현\n

일정 추가는 activity로 하기\n

일정 저장은 file로 하기\n
SQLIteDataBase를 쓰거나 SharedPreference를 쓰면 됨\n
일정 설정 activity를 종료하면 아예 main activity를 새로 열거나, 아니면 그냥 해당 view만 새로고침하기\n

EditDateActivity: 일정 추가 / 수정\n
key: 추가 유형(=logic의 수정 가능 여부)\n
날자 / 일정번호 (일정번호 비어있으면 새 일정,\n
만약 일정번호가 있으면 해당 일정의 데이터 불러오기)\n

EmailActivity는 안의 보관함에 버튼을 누르면 새 fragment를 여는 방식으로\n
email_new fragment: 새(받은) 이메일\n
email_storage fragment: 보관한 이메일\n

MailContentActivity: 각 메일 항목별로
key: 이메일 번호

메일 크롤링은 가능한데 데이터 정제가 좀 골때리는 관계로 일단은 디자인에만 집중하는 걸 목표로
이메일부분에도 Recyclerview 사용하기
